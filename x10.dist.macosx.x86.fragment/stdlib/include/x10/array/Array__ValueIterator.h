#ifndef __X10_ARRAY_ARRAY__VALUEITERATOR_H
#define __X10_ARRAY_ARRAY__VALUEITERATOR_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_ITERATOR_H_NODEPS
#include <x10/lang/Iterator.h>
#undef X10_LANG_ITERATOR_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace array { 

template<class FMGL(U)> class Array__ValueIterator;
template <> class Array__ValueIterator<void>;
template<class FMGL(U)> class Array__ValueIterator : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(rank);
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::lang::Iterator<FMGL(U)>::template itable<x10::array::Array__ValueIterator<FMGL(U)> > _itable_0;
    
    static x10::lang::Any::itable<x10::array::Array__ValueIterator<FMGL(U)> > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::array::Point> > > FMGL(regIt);
    
    x10aux::ref<x10::array::Array<FMGL(U)> > FMGL(array);
    
    void _constructor(x10aux::ref<x10::array::Array<FMGL(U)> > a);
    
    static x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> > _make(
             x10aux::ref<x10::array::Array<FMGL(U)> > a);
    
    virtual x10_boolean hasNext();
    virtual FMGL(U) next();
    x10_int rank();
    virtual x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> > x10__array__Array__ValueIterator____x10__array__Array__ValueIterator__this(
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
template <> class Array__ValueIterator<void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_ARRAY_ARRAY__VALUEITERATOR_H

namespace x10 { namespace array { 
template<class FMGL(U)>
class Array__ValueIterator;
} } 

#ifndef X10_ARRAY_ARRAY__VALUEITERATOR_H_NODEPS
#define X10_ARRAY_ARRAY__VALUEITERATOR_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Int.h>
#include <x10/lang/Iterator.h>
#include <x10/array/Point.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/lang/ClassCastException.h>
#include <x10/lang/Boolean.h>
#ifndef X10_ARRAY_ARRAY__VALUEITERATOR_H_GENERICS
#define X10_ARRAY_ARRAY__VALUEITERATOR_H_GENERICS
template<class FMGL(U)> template<class __T> x10aux::ref<__T> x10::array::Array__ValueIterator<FMGL(U)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> > this_ = new (memset(x10aux::alloc<x10::array::Array__ValueIterator<FMGL(U)> >(), 0, sizeof(x10::array::Array__ValueIterator<FMGL(U)>))) x10::array::Array__ValueIterator<FMGL(U)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_ARRAY__VALUEITERATOR_H_GENERICS
#ifndef X10_ARRAY_ARRAY__VALUEITERATOR_H_IMPLEMENTATION
#define X10_ARRAY_ARRAY__VALUEITERATOR_H_IMPLEMENTATION
#include <x10/array/Array__ValueIterator.h>



//#line 331 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.PropertyDecl_c
template<class FMGL(U)> typename x10::lang::Iterator<FMGL(U)>::template itable<x10::array::Array__ValueIterator<FMGL(U)> >  x10::array::Array__ValueIterator<FMGL(U)>::_itable_0(&x10::array::Array__ValueIterator<FMGL(U)>::equals, &x10::array::Array__ValueIterator<FMGL(U)>::hasNext, &x10::array::Array__ValueIterator<FMGL(U)>::hashCode, &x10::array::Array__ValueIterator<FMGL(U)>::next, &x10::array::Array__ValueIterator<FMGL(U)>::toString, &x10::array::Array__ValueIterator<FMGL(U)>::typeName);
template<class FMGL(U)> x10::lang::Any::itable<x10::array::Array__ValueIterator<FMGL(U)> >  x10::array::Array__ValueIterator<FMGL(U)>::_itable_1(&x10::array::Array__ValueIterator<FMGL(U)>::equals, &x10::array::Array__ValueIterator<FMGL(U)>::hashCode, &x10::array::Array__ValueIterator<FMGL(U)>::toString, &x10::array::Array__ValueIterator<FMGL(U)>::typeName);
template<class FMGL(U)> x10aux::itable_entry x10::array::Array__ValueIterator<FMGL(U)>::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterator<FMGL(U)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::array::Array__ValueIterator<FMGL(U)> >())};
template<class FMGL(U)> void x10::array::Array__ValueIterator<FMGL(U)>::_instance_init() {
    _I_("Doing initialisation for class: x10::array::Array__ValueIterator<FMGL(U)>");
    
}


//#line 332 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10FieldDecl_c

//#line 333 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10FieldDecl_c

//#line 335 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(U)> void x10::array::Array__ValueIterator<FMGL(U)>::_constructor(
                          x10aux::ref<x10::array::Array<FMGL(U)> > a)
{
    this->::x10::lang::Object::_constructor();
    {
        
        //#line 336 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.AssignPropertyCall_c
        FMGL(rank) = x10aux::nullCheck(a)->
                       FMGL(rank);
        {
         
        }
    }
    
    //#line 337 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> >)this)->
      FMGL(regIt) =
      (x10aux::nullCheck(a)->x10::array::Array<FMGL(U)>::iterator());
    
    //#line 338 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> >)this)->
      FMGL(array) =
      (__extension__ ({
        x10aux::ref<x10::array::Array<FMGL(U)> > __desugarer__var__0__ =
          a;
        
        //#line 338 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::array::Array<FMGL(U)> > __var32__;
        
        //#line 338 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Labeled_c
        goto __ret2239; __ret2239: 
        //#line 338 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Do_c
        do
        {
        {
            
            //#line 338 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(__desugarer__var__0__,
                                        X10_NULL)) &&
                !((x10aux::struct_equals(x10aux::nullCheck(__desugarer__var__0__)->
                                           FMGL(rank),
                                         x10aux::nullCheck(((x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> >)this))->
                                           FMGL(rank)))))
            {
                
                //#line 338 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Throw_c
                x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.array.Array[U]{self.rank==x10.array.Array.ValueIterator#this.rank}"))));
            }
            
            //#line 338 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Eval_c
            __var32__ =
              __desugarer__var__0__;
            
            //#line 338 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Branch_c
            goto __ret2239_end_;
        }
        goto __ret2239_next_; __ret2239_next_: ;
        }
        while (false);
        goto __ret2239_end_; __ret2239_end_: ;
        __var32__;
    }))
    ;
    
}
template<class FMGL(U)>
x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> > x10::array::Array__ValueIterator<FMGL(U)>::_make(
  x10aux::ref<x10::array::Array<FMGL(U)> > a)
{
    x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> > this_ = new (memset(x10aux::alloc<x10::array::Array__ValueIterator<FMGL(U)> >(), 0, sizeof(x10::array::Array__ValueIterator<FMGL(U)>))) x10::array::Array__ValueIterator<FMGL(U)>();
    this_->_constructor(a);
    return this_;
}



//#line 340 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(U)> x10_boolean x10::array::Array__ValueIterator<FMGL(U)>::hasNext(
  ) {
    
    //#line 340 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return x10::lang::Iterator<x10aux::ref<x10::array::Point> >::hasNext(x10aux::nullCheck(((x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> >)this)->
                                                                                             FMGL(regIt)));
    
}

//#line 341 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(U)> FMGL(U) x10::array::Array__ValueIterator<FMGL(U)>::next(
  ) {
    
    //#line 341 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(((x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> >)this)->
                               FMGL(array))->x10::array::Array<FMGL(U)>::__apply(
             x10::lang::Iterator<x10aux::ref<x10::array::Point> >::next(x10aux::nullCheck(((x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> >)this)->
                                                                                            FMGL(regIt))));
    
}

//#line 331 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(U)> x10_int x10::array::Array__ValueIterator<FMGL(U)>::rank(
  ) {
    
    //#line 331 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> >)this)->
             FMGL(rank);
    
}

//#line 331 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(U)> x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> >
  x10::array::Array__ValueIterator<FMGL(U)>::x10__array__Array__ValueIterator____x10__array__Array__ValueIterator__this(
  ) {
    
    //#line 331 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::Array__ValueIterator<FMGL(U)> >)this);
    
}
template<class FMGL(U)> const x10aux::serialization_id_t x10::array::Array__ValueIterator<FMGL(U)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::array::Array__ValueIterator<FMGL(U)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(U)> void x10::array::Array__ValueIterator<FMGL(U)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(regIt));
    buf.write(this->FMGL(array));
    buf.write(this->FMGL(rank));
    
}

template<class FMGL(U)> void x10::array::Array__ValueIterator<FMGL(U)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(regIt) = buf.read<x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::array::Point> > > >();
    FMGL(array) = buf.read<x10aux::ref<x10::array::Array<FMGL(U)> > >();
    FMGL(rank) = buf.read<x10_int>();
}

template<class FMGL(U)> x10aux::RuntimeType x10::array::Array__ValueIterator<FMGL(U)>::rtt;
template<class FMGL(U)> void x10::array::Array__ValueIterator<FMGL(U)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::array::Array__ValueIterator<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Iterator<FMGL(U)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(U)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.array.Array.ValueIterator";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
#endif // X10_ARRAY_ARRAY__VALUEITERATOR_H_IMPLEMENTATION
#endif // __X10_ARRAY_ARRAY__VALUEITERATOR_H_NODEPS
