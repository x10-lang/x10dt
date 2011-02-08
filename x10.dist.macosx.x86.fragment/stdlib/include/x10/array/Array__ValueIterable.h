#ifndef __X10_ARRAY_ARRAY__VALUEITERABLE_H
#define __X10_ARRAY_ARRAY__VALUEITERABLE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_ITERABLE_H_NODEPS
#include <x10/lang/Iterable.h>
#undef X10_LANG_ITERABLE_H_NODEPS
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(U)> class Array__ValueIterator;
} } 
namespace x10 { namespace array { 
template<class FMGL(U)> class Array__ValueIterator;
} } 
namespace x10 { namespace array { 

template<class FMGL(T)> class Array__ValueIterable;
template <> class Array__ValueIterable<void>;
template<class FMGL(T)> class Array__ValueIterable : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::lang::Iterable<FMGL(T)>::template itable<x10::array::Array__ValueIterable<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::array::Array__ValueIterable<FMGL(T)> > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::array::Array<FMGL(T)> > FMGL(out__);
    
    virtual x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator();
    virtual x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> > x10__array__Array__ValueIterable____x10__array__Array__ValueIterable__this(
      );
    virtual x10aux::ref<x10::array::Array<FMGL(T)> > x10__array__Array__ValueIterable____x10__array__Array__this(
      );
    void _constructor(x10aux::ref<x10::array::Array<FMGL(T)> > out__);
    
    static x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> > _make(
             x10aux::ref<x10::array::Array<FMGL(T)> > out__);
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class Array__ValueIterable<void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_ARRAY_ARRAY__VALUEITERABLE_H

namespace x10 { namespace array { 
template<class FMGL(T)>
class Array__ValueIterable;
} } 

#ifndef X10_ARRAY_ARRAY__VALUEITERABLE_H_NODEPS
#define X10_ARRAY_ARRAY__VALUEITERABLE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterable.h>
#include <x10/array/Array.h>
#include <x10/array/Array__ValueIterator.h>
#include <x10/array/Array__ValueIterator.h>
#ifndef X10_ARRAY_ARRAY__VALUEITERABLE_H_GENERICS
#define X10_ARRAY_ARRAY__VALUEITERABLE_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::array::Array__ValueIterable<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::array::Array__ValueIterable<FMGL(T)> >(), 0, sizeof(x10::array::Array__ValueIterable<FMGL(T)>))) x10::array::Array__ValueIterable<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_ARRAY__VALUEITERABLE_H_GENERICS
#ifndef X10_ARRAY_ARRAY__VALUEITERABLE_H_IMPLEMENTATION
#define X10_ARRAY_ARRAY__VALUEITERABLE_H_IMPLEMENTATION
#include <x10/array/Array__ValueIterable.h>


template<class FMGL(T)> typename x10::lang::Iterable<FMGL(T)>::template itable<x10::array::Array__ValueIterable<FMGL(T)> >  x10::array::Array__ValueIterable<FMGL(T)>::_itable_0(&x10::array::Array__ValueIterable<FMGL(T)>::equals, &x10::array::Array__ValueIterable<FMGL(T)>::hashCode, &x10::array::Array__ValueIterable<FMGL(T)>::iterator, &x10::array::Array__ValueIterable<FMGL(T)>::toString, &x10::array::Array__ValueIterable<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::array::Array__ValueIterable<FMGL(T)> >  x10::array::Array__ValueIterable<FMGL(T)>::_itable_1(&x10::array::Array__ValueIterable<FMGL(T)>::equals, &x10::array::Array__ValueIterable<FMGL(T)>::hashCode, &x10::array::Array__ValueIterable<FMGL(T)>::toString, &x10::array::Array__ValueIterable<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::array::Array__ValueIterable<FMGL(T)>::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterable<FMGL(T)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::array::Array__ValueIterable<FMGL(T)> >())};
template<class FMGL(T)> void x10::array::Array__ValueIterable<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::array::Array__ValueIterable<FMGL(T)>");
    
}


//#line 56 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10FieldDecl_c

//#line 344 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::Iterator<FMGL(T)> > x10::array::Array__ValueIterable<FMGL(T)>::iterator(
  ) {
    
    //#line 344 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return x10::array::Array__ValueIterator<FMGL(T)>::_make(((x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> >)this)->
                                                              FMGL(out__));
    
}

//#line 343 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> >
  x10::array::Array__ValueIterable<FMGL(T)>::x10__array__Array__ValueIterable____x10__array__Array__ValueIterable__this(
  ) {
    
    //#line 343 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> >)this);
    
}

//#line 343 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::array::Array<FMGL(T)> >
  x10::array::Array__ValueIterable<FMGL(T)>::x10__array__Array__ValueIterable____x10__array__Array__this(
  ) {
    
    //#line 343 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> >)this)->
             FMGL(out__);
    
}

//#line 343 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::array::Array__ValueIterable<FMGL(T)>::_constructor(
                          x10aux::ref<x10::array::Array<FMGL(T)> > out__)
{
    
    //#line 56 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> >)this)->
      FMGL(out__) =
      out__;
    this->::x10::lang::Object::_constructor();
    {
     
    }
    
}
template<class FMGL(T)>
x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> > x10::array::Array__ValueIterable<FMGL(T)>::_make(
  x10aux::ref<x10::array::Array<FMGL(T)> > out__)
{
    x10aux::ref<x10::array::Array__ValueIterable<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::array::Array__ValueIterable<FMGL(T)> >(), 0, sizeof(x10::array::Array__ValueIterable<FMGL(T)>))) x10::array::Array__ValueIterable<FMGL(T)>();
    this_->_constructor(out__);
    return this_;
}


template<class FMGL(T)> const x10aux::serialization_id_t x10::array::Array__ValueIterable<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::array::Array__ValueIterable<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::array::Array__ValueIterable<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(out__));
    
}

template<class FMGL(T)> void x10::array::Array__ValueIterable<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(out__) = buf.read<x10aux::ref<x10::array::Array<FMGL(T)> > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::array::Array__ValueIterable<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::array::Array__ValueIterable<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::array::Array__ValueIterable<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Iterable<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.array.Array.ValueIterable";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
#endif // X10_ARRAY_ARRAY__VALUEITERABLE_H_IMPLEMENTATION
#endif // __X10_ARRAY_ARRAY__VALUEITERABLE_H_NODEPS
