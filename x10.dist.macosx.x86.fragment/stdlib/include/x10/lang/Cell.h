#ifndef __X10_LANG_CELL_H
#define __X10_LANG_CELL_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 

template<class FMGL(T)> class Cell;
template <> class Cell<void>;
template<class FMGL(T)> class Cell : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    FMGL(T) FMGL(value);
    
    void _constructor(FMGL(T) x);
    
    static x10aux::ref<x10::lang::Cell<FMGL(T)> > _make(FMGL(T) x);
    
    virtual x10aux::ref<x10::lang::String> toString();
    virtual FMGL(T) __apply();
    virtual void __apply(FMGL(T) x);
    virtual void __set(FMGL(T) x);
    virtual FMGL(T) set(FMGL(T) x);
    virtual x10aux::ref<x10::lang::Cell<FMGL(T)> > x10__lang__Cell____x10__lang__Cell__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class Cell<void> : public x10::lang::Object {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    template<class FMGL(T)> static x10aux::ref<x10::lang::Cell<FMGL(T)> >
      make(
      FMGL(T) x);
    
    template<class FMGL(V)> static FMGL(V) __implicit_convert(x10aux::ref<x10::lang::Cell<FMGL(V)> > x);
    
    template<class FMGL(W)> static x10aux::ref<x10::lang::Cell<FMGL(W)> >
      __implicit_convert(
      FMGL(W) x);
    
    
};

} } 
#endif // X10_LANG_CELL_H

namespace x10 { namespace lang { 
template<class FMGL(T)> class Cell;
} } 

#ifndef X10_LANG_CELL_H_NODEPS
#define X10_LANG_CELL_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/String.h>
#ifndef X10_LANG_CELL_H_GENERICS
#define X10_LANG_CELL_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::lang::Cell<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Cell<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::Cell<FMGL(T)> >(), 0, sizeof(x10::lang::Cell<FMGL(T)>))) x10::lang::Cell<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_CELL_H_GENERICS
#ifndef X10_LANG_CELL_H_IMPLEMENTATION
#define X10_LANG_CELL_H_IMPLEMENTATION
#include <x10/lang/Cell.h>


template<class FMGL(T)> void x10::lang::Cell<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::Cell<FMGL(T)>");
    
}


//#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10FieldDecl_c
/**
     * The value stored in this cell.
     */

//#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::lang::Cell<FMGL(T)>::_constructor(FMGL(T) x)
{
    this->::x10::lang::Object::_constructor();
    {
     
    }
    
    //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::Cell<FMGL(T)> >)this)->
      FMGL(value) =
      x;
    
}
template<class FMGL(T)>
x10aux::ref<x10::lang::Cell<FMGL(T)> > x10::lang::Cell<FMGL(T)>::_make(
  FMGL(T) x)
{
    x10aux::ref<x10::lang::Cell<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::Cell<FMGL(T)> >(), 0, sizeof(x10::lang::Cell<FMGL(T)>))) x10::lang::Cell<FMGL(T)>();
    this_->_constructor(x);
    return this_;
}



//#line 42 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::String> x10::lang::Cell<FMGL(T)>::toString(
  ) {
    
    //#line 43 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10Return_c
    return ((((x10aux::string_utils::lit("Cell(")) + (x10aux::to_string(((x10aux::ref<x10::lang::Cell<FMGL(T)> >)this)->
                                                                          FMGL(value))))) + (x10aux::string_utils::lit(")")));
    
}

//#line 53 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::lang::Cell<FMGL(T)>::__apply(
  ) {
    
    //#line 53 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::lang::Cell<FMGL(T)> >)this)->
             FMGL(value);
    
}

//#line 61 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::Cell<FMGL(T)>::__apply(
  FMGL(T) x) {
    
    //#line 61 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::Cell<FMGL(T)> >)this)->FMGL(value) =
      x;
}

//#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::Cell<FMGL(T)>::__set(
  FMGL(T) x) {
    
    //#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::Cell<FMGL(T)> >)this)->x10::lang::Cell<FMGL(T)>::set(
      x);
}

//#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::lang::Cell<FMGL(T)>::set(
  FMGL(T) x) {
    
    //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::Cell<FMGL(T)> >)this)->
      FMGL(value) = x;
    
    //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10Return_c
    return x;
    
}

//#line 81 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10MethodDecl_c

//#line 92 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10MethodDecl_c

//#line 101 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10MethodDecl_c

//#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::Cell<FMGL(T)> >
  x10::lang::Cell<FMGL(T)>::x10__lang__Cell____x10__lang__Cell__this(
  ) {
    
    //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::lang::Cell<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::lang::Cell<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::lang::Cell<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::lang::Cell<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(value));
    
}

template<class FMGL(T)> void x10::lang::Cell<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(value) = buf.read<FMGL(T)>();
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::Cell<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::Cell<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::Cell<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.Cell";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 1, params, variances);
}
template<class FMGL(T)> x10aux::ref<x10::lang::Cell<FMGL(T)> >
  x10::lang::Cell<void>::make(FMGL(T) x)
{
    
    //#line 81 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10Return_c
    return x10::lang::Cell<FMGL(T)>::_make(x);
    
}
template<class FMGL(V)>
FMGL(V)
  x10::lang::Cell<void>::__implicit_convert(x10aux::ref<x10::lang::Cell<FMGL(V)> > x)
{
    
    //#line 92 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(x)->x10::lang::Cell<FMGL(V)>::__apply();
    
}
template<class FMGL(W)>
x10aux::ref<x10::lang::Cell<FMGL(W)> >
  x10::lang::Cell<void>::__implicit_convert(FMGL(W) x)
{
    
    //#line 101 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Cell.x10": x10.ast.X10Return_c
    return x10::lang::Cell<void>::template make<FMGL(W) >(
             x);
    
}
#endif // X10_LANG_CELL_H_IMPLEMENTATION
#endif // __X10_LANG_CELL_H_NODEPS
