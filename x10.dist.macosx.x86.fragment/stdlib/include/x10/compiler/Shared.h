#ifndef __X10_COMPILER_SHARED_H
#define __X10_COMPILER_SHARED_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace compiler { 

template<class FMGL(T)> class Shared;
template <> class Shared<void>;
template<class FMGL(T)> class Shared : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    FMGL(T) FMGL(x);
    
    void _constructor(FMGL(T) x);
    
    static x10aux::ref<x10::compiler::Shared<FMGL(T)> > _make(FMGL(T) x);
    
    virtual FMGL(T) get();
    virtual void set(FMGL(T) x);
    virtual x10aux::ref<x10::compiler::Shared<FMGL(T)> > x10__compiler__Shared____x10__compiler__Shared__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class Shared<void> : public x10::lang::Object {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_COMPILER_SHARED_H

namespace x10 { namespace compiler { 
template<class FMGL(T)> class Shared;
} } 

#ifndef X10_COMPILER_SHARED_H_NODEPS
#define X10_COMPILER_SHARED_H_NODEPS
#include <x10/lang/Object.h>
#ifndef X10_COMPILER_SHARED_H_GENERICS
#define X10_COMPILER_SHARED_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::compiler::Shared<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::compiler::Shared<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::compiler::Shared<FMGL(T)> >(), 0, sizeof(x10::compiler::Shared<FMGL(T)>))) x10::compiler::Shared<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_COMPILER_SHARED_H_GENERICS
#ifndef X10_COMPILER_SHARED_H_IMPLEMENTATION
#define X10_COMPILER_SHARED_H_IMPLEMENTATION
#include <x10/compiler/Shared.h>


template<class FMGL(T)> void x10::compiler::Shared<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::compiler::Shared<FMGL(T)>");
    
}


//#line 15 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Shared.x10": x10.ast.X10FieldDecl_c

//#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Shared.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::compiler::Shared<FMGL(T)>::_constructor(
                          FMGL(T) x) {
    this->::x10::lang::Object::_constructor();
    {
     
    }
    
    //#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Shared.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::compiler::Shared<FMGL(T)> >)this)->FMGL(x) = x;
    
}
template<class FMGL(T)> x10aux::ref<x10::compiler::Shared<FMGL(T)> > x10::compiler::Shared<FMGL(T)>::_make(
                          FMGL(T) x) {
    x10aux::ref<x10::compiler::Shared<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::compiler::Shared<FMGL(T)> >(), 0, sizeof(x10::compiler::Shared<FMGL(T)>))) x10::compiler::Shared<FMGL(T)>();
    this_->_constructor(x);
    return this_;
}



//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Shared.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::compiler::Shared<FMGL(T)>::get(
  ) {
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Shared.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::compiler::Shared<FMGL(T)> >)this)->
             FMGL(x);
    
}

//#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Shared.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::compiler::Shared<FMGL(T)>::set(
  FMGL(T) x) {
    
    //#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Shared.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::compiler::Shared<FMGL(T)> >)this)->FMGL(x) =
      x;
}

//#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Shared.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::compiler::Shared<FMGL(T)> >
  x10::compiler::Shared<FMGL(T)>::x10__compiler__Shared____x10__compiler__Shared__this(
  ) {
    
    //#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Shared.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::compiler::Shared<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::compiler::Shared<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::compiler::Shared<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::compiler::Shared<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(x));
    
}

template<class FMGL(T)> void x10::compiler::Shared<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(x) = buf.read<FMGL(T)>();
}

template<class FMGL(T)> x10aux::RuntimeType x10::compiler::Shared<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::compiler::Shared<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::compiler::Shared<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.compiler.Shared";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 1, params, variances);
}
#endif // X10_COMPILER_SHARED_H_IMPLEMENTATION
#endif // __X10_COMPILER_SHARED_H_NODEPS
